(ns scratch.subs
  (:require [re-frame.core :refer [reg-sub]]))

;; This subscription will return the :numbers from our database
(reg-sub
 ::all-numbers
 (fn [db _]
   (:numbers db)))

;; TODO: implement this subscription to get the odd numbers
(reg-sub
 ::odd-numbers
 identity)
