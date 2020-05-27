package de.neominik.uvl;

import clojure.java.api.Clojure;
import clojure.lang.IFn;
import de.neominik.uvl.ast.UVLModel;
import de.neominik.uvl.ast.ParseError;

public class UVLParser {

  static {
    IFn require = Clojure.var("clojure.core", "require");
    require.invoke(Clojure.read("de.neominik.uvl.parser"));
  }
  private static final IFn parser = Clojure.var("de.neominik.uvl.parser", "parse");

  /**
   * Parses the given text and returns an instance of a {@link UVLModel} or a {@link ParseError} on failure.
  */
  public static Object parse(String text) {
  	return parser.invoke(text);
  }
}
