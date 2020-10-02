(ns scratch.events
  (:require [re-frame.core   :refer [reg-event-db after]]
            [cljs.spec.alpha :as s]
            [scratch.db       :as db :refer [default-db]]
            [scratch.util     :as u]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Re-frame function helpers and interceptors
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn check-and-throw
  "This is a function that will verify that our database
   has valid state after any event dispatch."
  [a-spec db]
  (when-not (s/valid? a-spec db)
    (throw (ex-info (str "Spec check failed: " (s/explain-str a-spec db))
                    {}))))

;; This creates an interceptor of the function above to be used in re-frame.
(def check-spec-interceptor
  (after (partial check-and-throw ::db/db)))

(def interceptors
  [check-spec-interceptor])

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Re-frame event dispatches
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; The initialize event will take the default db (supplied in scratch.db)
;;  and set the re-frame app-db value to it. 
(reg-event-db
  ::initialize
  interceptors
  (fn [_ _]
    default-db))

;; This event increments every number
(reg-event-db
  ::inc-all
  interceptors
  (fn [db _]
    ;; Every reg-event-db must return an updated version of the app-db.
    ;; For this event, we want to increment every number in :numbers.
    ;; We can use (map ...) to perform a sequence comprehension on that data
    ;; and increment every value using the inc function. Using update, we
    ;; can provide the path in our db we wish to update.
    (update db :numbers #(map inc %))))

(reg-event-db
  ::dec-all
  interceptors
  (fn [db _]
    (update db :numbers #(map dec %))))
