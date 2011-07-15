(ns MyBot
  (:use ants))

(def directions [:north :east :west :south])

(defn log [msg]
  (binding [*out* *err*]
    (println msg)))

(defn food-close-to [ant]
  (first (sort-by #(distance % ant) (:food *game-state*))))

(defn movable-direction [ant directions]
  (first (filter #(can-move? ant %) directions)))

(defn move-random [ant]
  (move ant (movable-direction ant directions)))

(defn seek-food [ant food-location]
  (let [food-direction (movable-direction ant (direction ant food-location))]
    (if (nil? food-direction) (move-random ant) (move ant food-direction))))

(defn simple-bot []
  (log "running")
  (doseq [ant (my-ants)]
    (let [close-food (food-close-to ant)]
      (if (nil? close-food) (move-random ant) (seek-food ant close-food)))))

(start-game simple-bot)

