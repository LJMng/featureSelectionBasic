package featureSelection.basic.annotation.theory;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A theory proposed by Ke Gong, Zhi Xiao, Xia Zhong based on Soft Set Theory (see
 * <a href="https://www.sciencedirect.com/science/article/pii/S0898122199000565">
 * "Soft set theory—First results"</a> by D.MOLODTSOV).
 * <p>
 * Check out the paper
 * <a href="https://www.sciencedirect.com/science/article/pii/S089812211000581X">"The
 * bijective soft set with its operations"</a>.
 *
 * @author Benjamin_L
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface BijectiveSoftSet {}
