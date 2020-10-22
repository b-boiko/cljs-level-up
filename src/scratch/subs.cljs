(ns scratch.subs
  (:require [re-frame.core :refer [reg-sub]]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Subs introduced in Stage 1
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

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

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Subs introduced in Stage 2
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; This sub uses the clojure `first` function to get
;; the first item in a sequence.
(reg-sub
 ::first-number
 :<- [::all-numbers]
 (fn [all-numbers _]
   (first all-numbers)))

;; This subscription uses another function to do the same thing, just with
;; a little more work
;; We use `get` to get the item in the 0th index, or first position.
(reg-sub
 ::my-first-number
 :<- [::all-numbers]
 (fn [all-numbers _]
   (get all-numbers
        0)))

;; This sub uses the clojure `last` function to get
;; the last item in a sequence.
(reg-sub
 ::last-number
 :<- [::all-numbers]
 (fn [all-numbers _]
   (last all-numbers)))

(reg-sub
 ::second-number
 :<- [::all-numbers]
 (fn [all-numbers _]
   (second all-numbers)))

;; This sub uses the clojure `rest` function to get
;; everything but the first item.
(reg-sub
 ::rest-numbers
 :<- [::all-numbers]
 (fn [all-numbers _]
   (rest all-numbers)))
