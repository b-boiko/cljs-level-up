(ns scratch.views
  (:require [re-frame.core :refer [subscribe dispatch]]
            [scratch.subs  :as subs]
            [scratch.util  :refer [pad]]))

(defn button
  [text on-click]
  ;; TODO: create a button component that takes some text for a label
  ;; and also accepts a click event
  [:div])

(defn card
  [& {:keys [title text body]}]
  [:div.card
   [:div.header
    [:div.title title]
    [:div.text text]]
   [:div.body body]])

(defn all-numbers
  []
  (let [data @(subscribe [::subs/all-numbers])]
    [card
     :title "All Numbers"
     :text  "This shows every number"
     :body  (pad data)]))

(defn odd-numbers
  []
  ;; TODO: This component should show every odd number from the collection
  (let [data @(subscribe [::subs/odd-numbers])]
    [card
     :title "Odd Numbers"
     :text  "This shows every odd number"
     :body  "Replace this with the odd numbers from the number collection"]))

(defn app
  []
  [:div "Scratch App"
   ;; TODO: create two buttons, that allow for incrementing and decrementing
   [all-numbers]
   [odd-numbers]
   ;; TODO: create a new component that will show all the even numbers.
   ])
