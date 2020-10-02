(ns scratch.subs
  (:require [re-frame.core :refer [reg-sub]]))

;; This subscription will return the :numbers from our database.
(reg-sub
 ::all-numbers
 (fn [db _]
   (:numbers db)))

;; This subscription will consume the ::all-numbers and return only the odd ones.
(reg-sub
 ::odd-numbers
 :<- [::all-numbers]
 ;; The above is syntactic sugar that tells this subscription to consume the
 ;; output of the ::all-numbers subscription to use as the input for ::odd-numbers.
 ;; Re-frame performs aggressive caching of sub outputs, so reusing our subs
 ;; in this manner provides us incredible performance benefits.
 #_(fn [_ _]
     @(re-frame.core/subscribe [::all-numbers]))
 (fn [all-numbers _]
   ;; Take only the odd numbers from our db
   (filter odd? all-numbers)))

;; This subscription will consume the ::all-numbers and return only the even ones.
;; Refer to the notes above for the inner details.
(reg-sub
 ::even-numbers
 :<- [::all-numbers]
 (fn [all-numbers _]
   (filter even? all-numbers)))
