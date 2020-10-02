(ns scratch.db
  (:require [cljs.spec.alpha :as s]
            [scratch.util     :as u]))

(s/def ::numbers (s/coll-of number?))

;; This is the spec that defines the valid shape of our database.
(s/def ::db (s/keys :req-un [::numbers]
                    :opt-un []))

;; This will be the database value anytime that page loads into the browser.
(def default-db
  {:numbers (range 50)})
