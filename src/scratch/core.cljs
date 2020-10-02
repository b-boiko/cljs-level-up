(ns ^:figwheel-hooks scratch.core
  (:require [reagent.core   :as r]
            [reagent.dom    :as rdom]
            [re-frame.core  :refer [dispatch-sync]]
            [scratch.subs]
            [scratch.events :as events]
            [scratch.views  :as views]))

(enable-console-print!)

(defonce db
  (dispatch-sync [::events/initialize]))

(defn render
  "This is the entry point for the CLJS application.

   Our view code will be injected into the div.app found in our index.html file."
  []
  (rdom/render [views/app]
               (js/document.getElementById "app")))

(defn ^:after-load on-reload
  []
  (render))

(render)

