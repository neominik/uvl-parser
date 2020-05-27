(defproject uvl-parser "0.1.0-SNAPSHOT"
  :description "Small default parser lib for UVL"
  :url "https://github.com/neominik/uvl-parser"
  :license {:name "MIT License"
            :url "http://www.opensource.org/licenses/mit-license.php"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [instaparse "1.4.10"]
                 [com.wjoel/clj-bean "0.2.1"]]
  :repl-options {:init-ns de.neominik.uvl.parser}
  :profiles {:uberjar {:prep-tasks ["compile" "javac" "compile"]
                       :aot :all}}
  :java-source-paths ["java"])
