(defproject uvl-parser "0.1.0-SNAPSHOT"
  :description "Small default parser lib for UVL"
  :url "https://github.com/neominik/uvl-parser"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0" ;TODO tbd
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [instaparse "1.4.10"]
                 [com.wjoel/clj-bean "0.2.1"]]
  :repl-options {:init-ns de.neominik.uvl.parser}
  :profiles {:uberjar {:prep-tasks ["compile" "javac" "compile"]
                       :aot :all}}
  :java-source-paths ["java"])
