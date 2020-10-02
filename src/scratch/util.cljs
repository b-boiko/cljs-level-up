(ns scratch.util
  (:require [clojure.string :as string]))

(defn pad
  "This function will accept a collection and pad it with commas
   creating a human-readable list."
  [data]
  (string/join ", " data))
