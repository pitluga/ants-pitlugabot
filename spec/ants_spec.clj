(ns ants-spec
  (:use [speclj.core] [ants]))

(describe "Truth"

  (it "is true"
    (should true))

  (it "is not false"
    (should-not false)))

(run-specs)
