(ns cia.schemas.feedback
  (:require [cia.schemas.common :as c]
            [cia.schemas.relationships :as rel]
            [schema.core :as s]
            [schema-tools.core :as st]))

(s/defschema Feedback
  "Feedback on a Judgement or Verdict.  Is it wrong?  If so why?  Was
  it right-on, and worthy of confirmation?"
  {:id c/ID
   :judgement rel/JudgementReference
   (s/optional-key :source) s/Str
   :feedback (s/enum -1 0 1)
   :reason s/Str})

(s/defschema NewFeedback
  "Schema for submitting new Feedback"
  (st/merge
   (st/dissoc Feedback :id :judgement)
   {(s/optional-key :judgement) rel/JudgementReference}))

(s/defschema StoredFeedback
  "A feedback record at rest in the storage service"
  (st/merge Feedback
            {:owner s/Str
             :timestamp c/Time}))
