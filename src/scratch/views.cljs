(ns scratch.views
  (:require [scratch.views.stages.stage-1 :as stage-1]
            [scratch.views.stages.stage-2 :as stage-2]))

(defn app
  []
  [:div
   [:h2 "Scratch App"]
   [stage-1/stage]
   [stage-2/stage]])
