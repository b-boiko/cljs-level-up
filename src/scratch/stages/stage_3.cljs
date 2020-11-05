(ns scratch.views.stages.stage-3
  (:require [re-frame.core             :as rf]
            [scratch.views.common.card :refer [card]]))

(defn stage
  []
  [:<>
   [:h3 "Stage 3 --"]
   [:p "This stage will cover some basic elements of clojure spec coupled with re-frame best practices. To get a refresher on spec, check out "
    [:a {:href "https://clojure.org/guides/spec"} "this guide."]]
   [:p "The goal for this stage is to build a spec to accomodate the new `data` field in the app db. If you look in `db.cljs`, you will see additions to the `default-db` that contain some movies and users. Review the new data first and consider the structure of the spec, and how you want to organize and reuse your specs. Also, when possible, make your specs as narrow as possible. For example, something like a street address has a more specific format than just the name of a city."]
   [:p "The dev tools and console will be your friend through this. If the data does not conform well to the spec, you will see an error in the console, and an explanation of where the problem is. You will need to perform a hard refresh when you make changes to the spec however, since currently the only way the default db gets added is when the page first loads."]])
