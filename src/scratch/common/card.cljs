(ns scratch.views.common.card)

(defn card
  [& {:keys [title text body]}]
  [:div.card
   [:div.header
    [:div.title title]
    [:div.text text]]
   [:div.body body]])
