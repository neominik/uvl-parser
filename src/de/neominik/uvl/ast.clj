(ns de.neominik.uvl.ast
  (:require [clj-bean.core :refer [defbean]]
            [de.neominik.uvl.printer :refer :all]))

(defmacro defb [class-name typed-fields]
  (let [qualified-class-name (symbol (str "de.neominik.uvl.ast." class-name))
        [_ gen :as code] (macroexpand `(defbean ~qualified-class-name ~typed-fields))
        opts (apply hash-map (rest gen))
        toStringName (symbol (str (:prefix opts) "toString"))
        toStringImpl `(defn ~toStringName [~'this] (~(symbol (str (:prefix opts) "prn")) ~'this))
        print-on-repl `(defmethod print-method ~qualified-class-name ~'[it w] ~'(.write w (.toString it)))]
    `(~@code ~toStringImpl ~print-on-repl)))

(defb Import
  [[String namespace]
   [String alias]])

(gen-class :name de.neominik.uvl.ast.Group)
(defb Feature
  [[String name]
   [java.util.Map attributes]
   ["[Lde.neominik.uvl.ast.Group;" groups]])

(gen-class :name de.neominik.uvl.ast.UVLModel)
(defb UVLModel
  [[String namespace]
   ["[Lde.neominik.uvl.ast.Import;" imports]
   ["[Lde.neominik.uvl.ast.Feature;" rootFeatures]
   [java.util.Map allFeatures]
   ["[Lde.neominik.uvl.ast.UVLModel;" submodels]
   ["[Ljava.lang.Object;" constraints]
   ["[Ljava.lang.Object;" ownConstraints]])

(defb Group
  [[String type]
   [int lower]
   [int upper]
   ["[Lde.neominik.uvl.ast.Feature;" children]])

(defb Not
  [[Object child]])

(defb And
  [[Object left]
   [Object right]])

(defb Or
  [[Object left]
   [Object right]])

(defb Impl
  [[Object left]
   [Object right]])

(defb Equiv
  [[Object left]
   [Object right]])

(defb ParseError
  [[int line]
   [int column]
   [String text]
   [java.util.List expected]])
