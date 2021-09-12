package featureSelection.basic.model.universe.generator;

import featureSelection.basic.model.universe.instance.IncompleteInstance;
import featureSelection.basic.model.universe.instance.Instance;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;

import java.io.*;
import java.util.*;

/**
 * An {@link Instance} generator. (Improved version)
 *
 * @author Benjamin_L
 * @see Instance
 * @see IncompleteInstance
 */
@Slf4j
public class UniverseGeneratorImp {
	@Getter
	@Setter
	private UniverseGeneratingGuidance generatingInfo;
	/**
	 * The universe instance list
	 */
	private LinkedList<Instance> instanceList = new LinkedList<>();

	public UniverseGeneratorImp() {
		reset();
	}

	/**
	 * Reset the generator;
	 */
	public void reset() {
		instanceList.clear();
		Instance.resetID();
		generatingInfo = null;
	}

	/**
	 * Input and set the dataset from file line by line.
	 *
	 * @param file          The file to be read and set
	 * @param regex         The symbol to seperate each type
	 * @param decisionIndex The index of the decision value(starts from 1, -1 as the last one).
	 * @param missingValue  The symbol that represents missing value in the dataset. /
	 *                      <code>null</code> if no missing value.
	 * @throws Exception if file can't be read successfully. Or the first/last index
	 *                   is illegal.
	 */
	public void setDataSetWithFileByLines(
			File file, String regex, int decisionIndex, String missingValue
	) throws Exception {
		if (!file.exists()) throw new FileNotFoundException(file.getAbsolutePath());

		// Set instance ID if it is set to continue to generate.
		if (generatingInfo != null && generatingInfo.getLastInstanceID() > 1) {
			Instance.setID(generatingInfo.getLastInstanceID() + 1);
			log.info("Universe ID -> " + Instance.getNumCounter());
		}

		instanceList.clear();

		if (file.getName().endsWith(".csv")) {
			try (
					CSVParser csvParser = CSVFormat.DEFAULT.parse(
							new BufferedReader(
									new InputStreamReader(FileUtils.openInputStream(file), "UTF-8")
							)
					)
			) {
				for (CSVRecord csvRecord : csvParser) {
					String[] lineData = new String[csvRecord.get(csvRecord.size() - 1).isEmpty() ? csvRecord.size() - 1 : csvRecord.size()];
					Iterator<String> itr = csvRecord.iterator();
					for (int i = 0; i < lineData.length; i++) lineData[i] = itr.next();
					addInstance(lineData, decisionIndex, missingValue);
				}
			}
		} else {
			String line;
			LineIterator lineIterator = IOUtils.lineIterator(FileUtils.openInputStream(file), "UTF-8");
			while (lineIterator.hasNext()) {
				line = lineIterator.next();
				if (line != null) addInstance(line.split(regex), decisionIndex, missingValue);
			}
		}
		generatingInfo.setLastInstanceID(instanceList.getLast().getNum());
	}

	/**
	 * Input and set the data set from file line by columns.
	 * <p>
	 * <strong>PS: </strong><code>generatingInfo</code> is now updated in this method.
	 *
	 * @param dataset       The file to be read and set
	 * @param regex         The symbol to seperate each type
	 * @param decisionIndex The index of the decision value(starts from 1, -1 as the last one).
	 * @throws Exception if file can't be read successfully. Or the first/last index
	 *                   is illegal.
	 * @See UniverseGeneratorImp#setDataSetWithFileByLines(File, String, int)
	 * @deprecated This method is functioning much slowly than {@link
	 * UniverseGeneratorImp#setDataSetWithFileByLines(File, String, int,
	 * String)}
	 */
	public void setDataSetWithFileByColumns(File dataset, String regex, int decisionIndex) throws IOException {
		if (!dataset.exists()) throw new FileNotFoundException(dataset.getAbsolutePath());

		int row = analyzeInstanceLength(dataset);
		int column = analyzeAttributeLength(dataset, regex);
		log.info("Dataset : {}", dataset.getName());
		log.info("Dataset scale: {} x {} = {}", row, column, row * column);

		// GC
		System.gc();

		String data;
		int[][] instanceArray = new int[row][column];
		for (int i = 0; i < row; i++) instanceArray[i] = new int[column];

		if (decisionIndex < 0)
			decisionIndex = column + decisionIndex + 1;

		int rowPointer;
		LineIterator lineIterator;
		Map<String, Integer> dataNumber;
		for (int col = 1; col <= column; col++) {
			dataNumber = columnValueMap(dataset, regex, row, col - 1);
			lineIterator = IOUtils.lineIterator(FileUtils.openInputStream(dataset), "UTF-8");

			rowPointer = 0;
			while (lineIterator.hasNext()) {
				data = lineIterator.next().split(regex)[col - 1];

				if (col == decisionIndex) {
					instanceArray[rowPointer][0] = dataNumber.get(data);
				} else if (col > decisionIndex) {
					instanceArray[rowPointer][col - 1] = dataNumber.get(data);
				} else {
					instanceArray[rowPointer][col] = dataNumber.get(data);
				}
				rowPointer++;
			}
		}

		// GC
		data = null;
		dataNumber = null;
		lineIterator = null;
		System.gc();

		for (int i = 0; i < instanceArray.length; i++)
			instanceList.add(new Instance(instanceArray[i]));

		// GC
		System.gc();
	}

	/**
	 * Return a list of universe instances.
	 */
	public List<Instance> universe() {
		Instance.resetID();
		return instanceList;
	}

	/**
	 * Add an {@link Instance} by the given parameters. <code>columnMap</code> will be updated if
	 * necessary.
	 * <p>
	 * <strong>Notice: </strong>
	 * Not considering {@link IncompleteInstance()} in this method, all string in <code>data</code>
	 * is transfered into integers >= 0. Otherwise, use {@link #addInstance(String[], int, String)} if
	 * involving missing value.
	 *
	 * @param data          A line of data in {@link String[]}.
	 * @param decisionIndex The index of decision at column.(Starts from 1, -1 as the last column).
	 * @throws Exception if the column number of <code>data</code> is incompatible with current {@link Instance}.
	 * @see #addInstance(String[], int, String)
	 */
	public void addInstance(String[] data, int decisionIndex) throws Exception {
		addInstance(data, decisionIndex, null);
	}

	/**
	 * Add an {@link Instance} by the given parameters. <code>columnMap</code> will
	 * be updated automatically if necessary.
	 *
	 * @param data          A line of data in {@link String[]}.
	 * @param decisionIndex The index of decision at column.(Starts from 1, -1 as the last column).
	 * @param missingValue  The symbol that represents missing value in the dataset.
	 * @throws Exception if the column number of <code>data</code> is incompatible
	 *                   with current {@link Instance}.
	 * @see Instance
	 * @see IncompleteInstance
	 * @see UniverseGeneratingGuidance
	 */
	public void addInstance(String[] data, int decisionIndex, String missingValue)
			throws Exception {
		int column = previousUniverseColumnNumber();
		if (column != 0 && data.length != column) {
			throw new Exception("Invalid column number, " + column + " is expected: " + data.length);
		} else {
			if (column == 0) {
				// Initiate columns.
				column = data.length;
				generatingInfo = new UniverseGeneratingGuidance(column);
			}

			if (decisionIndex < 0)
				decisionIndex = column + decisionIndex + 1;

			boolean hasMissingValue = false;
			int[] instanceData = new int[column];
			Map<String, Integer>[] columnMap = generatingInfo.getColumnValueMap();
			for (int col = 1; col <= column; col++) {
				// Search for reflected value in columnMap.
				Integer value = columnMap[col - 1].get(data[col - 1]);
				// If reflected value is not found, reflect value.
				if (value == null) {
					// If value is missing data.
					if (missingValue != null && missingValue.equals(data[col - 1])) {
						value = IncompleteInstance.MISSING_VALUE;
						columnMap[col - 1].putIfAbsent(missingValue, value);
						hasMissingValue = true;
						// Else value is not missing data.
					} else {
						columnMap[col - 1].put(data[col - 1], value = columnMap[col - 1].size());
					}
				}
				columnValue2InstanceData(col, instanceData, decisionIndex, value);
			}

			// Add the instance into list.
			instanceList.add(
					hasMissingValue ?
							new IncompleteInstance(instanceData) :
							new Instance(instanceData)
			);
		}
	}

	/**
	 * Get the attribute length of the given file.
	 *
	 * @param file  The dataset {@link File}.
	 * @param regex The symbol to split data in each column.
	 * @return the attribute length.
	 * @throws IOException if exceptions occur when reading the file.
	 */
	private int analyzeAttributeLength(File file, String regex) throws IOException {
		String line = IOUtils.lineIterator(FileUtils.openInputStream(file), "UTF-8")
				.nextLine();
		return line.split(regex).length;
	}

	/**
	 * Get the data row number of the given file.
	 *
	 * @param file The dataset {@link File}.
	 * @return the data row number.
	 * @throws IOException if exceptions occur when reading the file.
	 */
	private int analyzeInstanceLength(File file) throws IOException {
		int instance = 0;
		String data;
		LineIterator lineIterator = IOUtils.lineIterator(FileUtils.openInputStream(file), "UTF-8");
		while (lineIterator.hasNext()) {
			data = lineIterator.nextLine();
			if (data.trim().length() > 0) instance++;
		}
		return instance;
	}

	private Map<String, Integer> columnValueMap(
			File file, String regex, int rowInTotal, int column
	) throws IOException {
		String data;
		Integer number;
		Map<String, Integer> data2Number = new HashMap<>(rowInTotal);
		LineIterator lineIterator = IOUtils.lineIterator(FileUtils.openInputStream(file), "UTF-8");
		while (lineIterator.hasNext()) {
			data = lineIterator.nextLine()
					.split(regex)
					[column];
			number = data2Number.get(data);
			if (number == null) data2Number.put(data, data2Number.size());
		}
		return data2Number;
	}

	/**
	 * Fill column value into universe instance data(i.e. <code>instance</code>).
	 *
	 * @param col           The column number. (Starts from 1)
	 * @param instance      The instance/data in {@link Instance}.
	 * @param decisionIndex The decision index. (Starts from 1)
	 * @param value         The value to be filled.
	 */
	private void columnValue2InstanceData(int col, int[] instance, int decisionIndex, int value) {
		if (col == decisionIndex) instance[0] = value;
		else if (col > decisionIndex) instance[col - 1] = value;
		else instance[col] = value;
	}

	private int previousUniverseColumnNumber() {
		return (generatingInfo == null ? 0 : generatingInfo.getColumnSize());
	}
}
