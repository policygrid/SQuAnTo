package uk.ac.man.cs.mig.coode.owldoc.gen;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jan 18, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class KeyWords {

	private static final String PRE = "<span class=\"" + StyleSheetGenerator.KEYWORD_CLASS + "\">";

	private static final String POST = "</span>";

	public static final String SOME = PRE + "SOME" + POST;

	public static final String ALL = PRE + "ONLY" + POST;

	public static final String HAS = PRE + "HAS" + POST;

	public static final String SPACE = "&nbsp;";

	public static final String AND = PRE + "AND" + POST;

	public static final String OR = PRE + "OR" + POST;

	public static final String NOT = PRE + "NOT" + POST;

	public static final String MIN = PRE + "MIN" + POST;

	public static final String EQ = PRE + "EQ" + POST;

	public static final String MAX = PRE + "MAX" + POST;



}

