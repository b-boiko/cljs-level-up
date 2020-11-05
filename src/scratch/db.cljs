(ns scratch.db
  (:require [cljs.spec.alpha :as s]
            [scratch.util     :as u]))

(s/def ::numbers (s/coll-of number?))

;; This is the spec that defines the valid shape of our database.
(s/def ::db (s/keys :req-un [::numbers]
                    :opt-un []))

;; This will be the database value anytime that page loads into the browser.
(def default-db
  {:numbers (vec (range 50))
   :data    {:users  [{:id       1
                       :name     "Terrence Davis"
                       :email    "tdavis@email.com"
                       :role     :admin
                       :birthday "03-21-1989"}
                      {:id       2
                       :name     "Emily Coley"
                       :email    "emilyc1991@otheremail.com"
                       :role     :user
                       :birthday "11-01-1991"}
                      {:id       3
                       :name     "Darius Winters"
                       :email    "winteriscoming@email.com"
                       :role     :user
                       :birthday "06-12-1990"}
                      {:id       4
                       :name     "Melanie Conrad"
                       :email    "mc1987@email.com"
                       :role     :user
                       :birthday "09-28-1987"}]
             :movies [{:year         2020
                       :title        "Parasite"
                       :director     "Bong Joon-ho"
                       :cast         ["Song Kang-ho"
                                      "Lee Sun-kyun"
                                      "Cho Yeo-jeong"
                                      "Choi Woo-shik"
                                      "Park So-dam"
                                      "Lee Jung-eun"
                                      "Jang Hye-jin"]
                       :release      "05-21-2019"
                       :running-time 132
                       :budget       11.4
                       :box-office   264.4}
                      {:year         2019
                       :title        "Green Book"
                       :director     "Peter Farrelly"
                       :cast         ["Viggo Mortensen"
                                      "Mahershala Ali"
                                      "Linda Cardellini"]
                       :release      "09-11-2018"
                       :running-time 130
                       :budget       23.0
                       :box-office   327.9}
                      {:year         2018
                       :title        "The Shape of Water"
                       :director     "Guillermo del Toro"
                       :cast         ["Sally Hawkins"
                                      "Michael Shannon"
                                      "Richard Jenkins"
                                      "Doug Jones"
                                      "Michael Stuhlbarg"
                                      "Octavia Spencer"]
                       :release      "08-31-2017"
                       :running-time 123
                       :budget       19.5
                       :box-office   195.3}
                      {:year         2017
                       :title        "Moonlight"
                       :director     "Barry Jenkins"
                       :cast         ["Trevante Rhodes"
                                      "André Holland"
                                      "Janelle Monáe"
                                      "Ashton Sanders"
                                      "Jharrel Jerome"
                                      "Naomie Harris"
                                      "Mahershala Ali"]
                       :release      "10-21-2016"
                       :running-time 111
                       :budget       1.2
                       :box-office   65.3}]}})
