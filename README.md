# UVL - Universal Variability Language

This is a small default library used to parse and print the Universal Variability Language (UVL).
It is written in Clojure, but can also be used from any JVM language, as it exposes a Java API.

Under the hood it uses [instaparse](https://github.com/Engelberg/instaparse) as the parsing library.
The grammar in EBNF form is located in `resources/uvl.bnf`

## Building a jar

To create a Java-consumable jar, the [Leiningen](https://leiningen.org) build tool is used.
`lein` will compile first the Clojure source, and then the Java source.
Both compiled Java and Clojure source will be packaged into a jar.

This is achieved through the uberjar task:

```
$ lein uberjar
Compiling de.neominik.uvl.ast
Compiling de.neominik.uvl.parser
Compiling de.neominik.uvl.transform
Compiling 1 source files to uvl-clj/target/classes
Created uvl-clj/target/uvl-parser-0.1.0-SNAPSHOT.jar
Created uvl-clj/target/uvl-parser-0.1.0-SNAPSHOT-standalone.jar
```

The `standalone.jar` includes all dependencies, while the other jar ships only the code of the uvl-parser itself, without Clojure or instaparse.

## Usage from Java
The class `de.neominik.uvl.UVLParser` exposes the static method `parse(String)` which will return an instance of a `de.neominik.uvl.UVLModel` on success or a `de.neominik.uvl.ParseError` when the input didn't comply to the grammar.

Printing is implemented in the `toString()`methods of the different model elements in the `UVLModel`.

## License

Copyright © 2020 Dominik Engelhardt

This software is released under the MIT License (see the file LICENSE for details).
Integrating it into your tool by reusing any part of it is strongly encouraged!
