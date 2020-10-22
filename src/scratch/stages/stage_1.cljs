(ns scratch.views.stages.stage-1
  (:require [re-frame.core               :as rf]
            [scratch.subs                :as subs]
            [scratch.events              :as events]
            [scratch.util                :refer [pad]]
            [scratch.views.common.button :refer [button]]
            [scratch.views.common.card   :refer [card]]))

(defn all-numbers
  []
  (let [data @(rf/subscribe [::subs/all-numbers])]
    [card
     :title "All Numbers"
     :text  "This shows every number"
     :body  (pad data)]))

(defn odd-numbers
  []
  (let [data @(rf/subscribe [::subs/odd-numbers])]
    [card
     :title "Odd Numbers"
     :text  "This shows every odd number"
     :body  (pad data)]))

(defn even-numbers
  []
  (let [data @(rf/subscribe [::subs/even-numbers])]
    [card
     :title "Even Numbers"
     :text  "This shows every even number"
     :body  (pad data)]))


(defn stage
  []
  [:<>
   [:h3 "Stage 1 --"]
   [:p "Intro stage just meant to get used to the format."]
   [:div
    [button "Increment" #(rf/dispatch [::events/inc-all])]
    [button "Decrement" #(rf/dispatch [::events/dec-all])]]
   [all-numbers]
   [odd-numbers]
   [even-numbers]])
