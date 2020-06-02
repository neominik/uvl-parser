package de.neominik.uvl;

import clojure.java.api.Clojure;
import clojure.lang.IFn;
import de.neominik.uvl.ast.Feature;
import de.neominik.uvl.ast.ParseError;
import de.neominik.uvl.ast.UVLModel;
import java.util.function.Function;
import java.util.Map;

public class UVLParser {

  static {
    IFn require = Clojure.var("clojure.core", "require");
    require.invoke(Clojure.read("de.neominik.uvl.parser"));
  }
  private static final IFn parser = Clojure.var("de.neominik.uvl.parser", "parse");
  private static final IFn fileLoader = Clojure.var("de.neominik.uvl.parser", "file-loader");

  /**
   * Parses the given text and returns an instance of a {@link UVLModel} or a {@link ParseError} on failure.
   * Uses the provided callback function to resolve namespace names from imports to the text from the correct file.
   * This is the recommended arity of the function to make sure, file resolution works in your project structure.
   */
  public static Object parse(String text, Function<String, String> fileLoader) {
  	return parser.invoke(text, fileLoader);
  }

  /**
   * Parses the given text and returns an instance of a {@link UVLModel} or a {@link ParseError} on failure.
   * Uses the provided absolute path to resolve imports. See {@link #parse(String,Function)} for more control.
   */
  public static Object parse(String text, String absolutePath) {
  	return parser.invoke(text, fileLoader.invoke(absolutePath));
  }

  /**
   * Parses the given text and returns an instance of a {@link UVLModel} or a {@link ParseError} on failure.
   * Uses the current working directory to resolve imports. See {@link #parse(String,String)} or
   * {@link #parse(String,Function)} for more reliable options.
   */
  public static Object parse(String text) {
  	return parser.invoke(text);
  }

  /**
   * Resolves the given feature to the correct namespace.
   */
  public static Feature resolve(Feature f, UVLModel m) {
  	return (Feature) m.getAllFeatures().get(f.getName());
  }

  /**
   * @return the attribute map for the given feature
   */
  @SuppressWarnings("unchecked")
  public static Map<String, Object> getAttributes(Feature f) {
  	return (Map<String, Object>) f.getAttributes();
  }
}
