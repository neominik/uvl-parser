(ns de.neominik.uvl.transform
  (:require [clojure.string :as s]
            [de.neominik.uvl.ast :refer :all])
  (:import [de.neominik.uvl.ast
            UVLModel
            Import
            Feature
            Group
            Not
            And
            Or
            Impl
            Equiv]))

(defn xf-ref [& ids] ;TODO resolve references
  (s/join "." ids))

(defn xf-featuremodel [& kvs]
  (let [{:keys [Ns imports features constraints] :or {imports [] features [] constraints []}} (into {} kvs)
        ns (or Ns (some-> (first features) .getName) "")]
    (UVLModel. ns imports features constraints)))

(defn xf-import
  ([namespace] (Import. namespace namespace))
  ([namespace [_ alias]] (Import. namespace alias)))
(defn xf-imports
  ([& imports] [:imports imports])
  ([] [:imports []]))

(defn xf-feature [name & kvs]
  (let [{:keys [attributes groups] :or {attributes {} groups []}} (into {} kvs)]
    (Feature. name attributes groups)))
(defn xf-features
  ([& features] [:features features])
  ([] [:features []]))

(defn xf-attribute
  ([[_ k] v] [k v])
  ([[_ k]] [k true]))
(defn xf-attributes [& keyvals]
  [:attributes (into {} keyvals)])

(defn xf-group [type & children] (Group. type children))
(defn xf-groups [& groups]
  [:groups groups])

(defn xf-constraints
  ([& constraints] [:constraints constraints])
  ([] [:constraints []]))
(defn xf-not [x] (Not. x))
(defn xf-and [x y] (And. x y))
(defn xf-or [x y] (Or. x y))
(defn xf-impl [x y] (Impl. x y))
(defn xf-equiv [x y] (Equiv. x y))

(def transform-map {:FeatureModel xf-featuremodel
                    :REF xf-ref
                    :Imports xf-imports
                    :Import xf-import

                    :Features xf-features
                    :FeatureSpec xf-feature
                    :Attributes xf-attributes
                    :Attribute xf-attribute
                    :Value identity
                    :Boolean clojure.edn/read-string
                    :Number clojure.edn/read-string
                    :String identity
                    :Vector vector
                    :Group xf-group
                    :Groups xf-groups

                    :Constraints xf-constraints
                    :Not xf-not
                    :And xf-and
                    :Or xf-or
                    :Impl xf-impl
                    :Equiv xf-equiv})
