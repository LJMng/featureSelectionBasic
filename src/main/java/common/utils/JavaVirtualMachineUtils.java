package common.utils;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;

public class JavaVirtualMachineUtils {

	public static class Memory {
		
		private static MemoryUsage getHeapMemoryUsage() {
			return ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
		}
		
		public static long maxInByte() {
			return getHeapMemoryUsage().getMax();
		}
		
		public static long usedInByte() {
			return getHeapMemoryUsage().getUsed();
		}
		
		public static long committedInByte() {
			return getHeapMemoryUsage().getCommitted();
		}
		
	}
	
}
