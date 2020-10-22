(ns scratch.views.common.button)

(defn button
  [text on-click]
  [:button {:on-click on-click}
   text])
