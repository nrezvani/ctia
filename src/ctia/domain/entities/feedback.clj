(ns ctia.domain.entities.feedback
  (:require
    [ctia.properties :refer [get-http-show]]
    [ctim.domain.id :as id]))

(def short-id->long-id
  (id/factory:short-id->long-id :feedback get-http-show))

(defn with-long-id [entity]
  (update entity :id short-id->long-id))

(defn page-with-long-id [m]
  (update m :data (partial map with-long-id)))
