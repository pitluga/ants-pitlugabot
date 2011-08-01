(ns pathfinder-spec
  (:use [speclj.core] [pathfinding]))

(describe "parse-board"
  (it "returns a hash containing the water"
    (let [expected {:water #{[0 0]}}
          actual   (parse-board "w")]
      (should= expected actual)))

  (it "returns a hash containing multiple waters"
    (let [expected {:water #{[0 0] [0 1] }}
          actual   (parse-board "ww")]
      (should= expected actual)))

  (it "returns a game state with water in different rows"
    (let [expected {:water #{[0 0] [1 0] }}
          actual   (parse-board "w\nw")]
      (should= expected actual))))

(def dead-simple ".....
.....
.....
...w.
.....")

(describe "a dead simple map"
  (it "finds the path without obstruction"
    (let [board (parse-board dead-simple)]
      (should (= [:south :east] (find-path board [2 0] [2 1]))))))

(run-specs)
