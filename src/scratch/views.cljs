(ns scratch.views
  (:require [re-frame.core  :refer [subscribe dispatch]]
            [scratch.subs   :as subs]
            [scratch.events :as events]
            [scratch.util   :refer [pad]]))

(defn button
  [text on-click]
  [:button {:on-click on-click}
   text])

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
  (let [data @(subscribe [::subs/odd-numbers])]
    [card
     :title "Odd Numbers"
     :text  "This shows every odd number"
     :body  (pad data)]))

(defn even-numbers
  []
  (let [data @(subscribe [::subs/even-numbers])]
    [card
     :title "Even Numbers"
     :text  "This shows every even number"
     :body  (pad data)]))

(defn app
  []
  [:div "Scratch App"
   [:div
    [button "Increment" #(dispatch [::events/inc-all])]
    [button "Decrement" #(dispatch [::events/dec-all])]]
   [all-numbers]
   [odd-numbers]
   [even-numbers]])
