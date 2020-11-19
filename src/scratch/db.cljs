(ns scratch.db
  (:require [cljs.spec.alpha :as s]
            [scratch.util    :as u]))

(s/def ::numbers (s/coll-of number?))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Common Specs
;; Any reused or potentially verbose specs benefit from being turned into
;; common specs outside of any hierarchy. This helps reduce clutter in the
;; actual specs defining the data, and improves composability.
;; Since the specs below are either used in multiple locations or just a bit
;; complicated, listing them here is a good pattern.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Any name is simply treated as a string
(s/def ::name string?)
;; The email spec is a regex to match (simply) valid emails 
(s/def ::email (partial re-matches #"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,63}$"))
;; The date spec is a regex that matches MM-DD-YYYY
(s/def ::date (partial re-matches #"^(0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-9]|3[0-1])-(19|20)[0-9][0-9]$"))
;; The year spec only allows numbers from 1900 - 2020
(s/def ::year #(<= 1900 % 2020))
;; Money is treated as a double (for talking about money in millions of dollars)
(s/def ::money double?)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; User specs
;; This spec and the use of namespace keywords starting with :user/ gives us
;; a hierarchy to explain the context in which a spec lives, and prevents
;; collisions if two specs of the same name exist. Let's say movie also had an
;; id component. If we just called :user/id ::id, then we have a potential
;; conflict with the movie. Having two keys like :user-id and :movie-id would
;; work, but clojure gives us namespace keywords specifically to solve naming
;; issues like this, so we would opt to use :user/id and :movie/id.
;; Most of the specs below end up using the common specs above.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; The user id is simply an integer
(s/def :user/id int?)
;; The user name uses the common name spec
(s/def :user/name ::name)
;; The user email uses the common email spec
(s/def :user/email ::email)
;; The user email is an enumeration, so we use a set of those valid options
(s/def :user/role #{:admin :user})
;; The user birthday is the common date spec
(s/def :user/birthday ::date)

;; A user is a map that contains all keys used above
(s/def ::user (s/keys :req-un [:user/id
                               :user/name
                               :user/email
                               :user/role
                               :user/birthday]))
;; The Users spec is a collection (vector) of the user spec above
(s/def ::users (s/coll-of ::user :kind vector?))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Movie specs
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(s/def :movie/year ::year)
(s/def :movie/title string?)
;; The movie director uses the common name spec
(s/def :movie/director ::name)
;; The cast is a collection of actors' names
(s/def :movie/cast (s/coll-of ::name))
(s/def :movie/release ::date)
(s/def :movie/running-time int?)
;; These two use the common money spec
(s/def :movie/budget ::money)
(s/def :movie/box-office ::money)

;; All of the above specs are required in the user
(s/def ::movie (s/keys :req-un [:movie/year
                                :movie/title
                                :movie/director
                                :movie/cast
                                :movie/release
                                :movie/running-time
                                :movie/budget
                                :movie/box-office]))
;; Movies is a collection (vector) of the movie spec above
(s/def ::movies (s/coll-of ::movie :kind vector?))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; The entire data spec put together just needs to have a users and movies key
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(s/def ::data (s/keys :req-un [::users
                               ::movies]))

;; This is the spec that defines the valid shape of our database.
;; We have included ::data into this db spec
(s/def ::db (s/keys :req-un [::numbers
                             ::data]))

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
