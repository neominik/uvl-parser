(ns de.neominik.uvl.ast
  (:require [clj-bean.core :refer [defbean]]))

(defmacro defb [class-name typed-fields]
  (let [qualified-class-name (symbol (str "de.neominik.uvl.ast." class-name))
        [_ gen :as code] (macroexpand `(defbean ~qualified-class-name ~typed-fields))
        opts (apply hash-map (rest gen))
        toStringName (symbol (str (:prefix opts) "toString"))
        toStringImpl `(defn ~toStringName [~'this] (str (bean ~'this)))
        print-on-repl `(defmethod print-method ~qualified-class-name ~'[it w] ~'(.write w (.toString it)))]
    `(~@code ~toStringImpl ~print-on-repl)))

(defb UVLModel
  [[String namespace]
   [java.util.List imports]
   [java.util.List rootFeatures]
   [java.util.List constraints]])

(defb Import
  [[String namespace]
   [String alias]])

(defb Feature
  [[String name]
   [java.util.Map attributes]
   [java.util.List groups]])

(defb Group
  [[String type]
   [java.util.List children]])

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
