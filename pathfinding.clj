(ns pathfinding
  (:require [clojure.string :as str])
  (:use [ants]))

(defn- parse-column [col value]
   (let [tile-type (map-tiles (.toString value))]
     (if (nil? tile-type) [] [tile-type col])))

(defn- parse-row [row cols]
  (let [typed-cols (remove empty? (map-indexed parse-column cols))]
    (map (fn [pair] [(first pair) [row (last pair)]]) typed-cols)))

(defn- to-game-state [game-state item]
  (if (seq? item)
    (reduce to-game-state game-state item)
    (if (nil? (game-state (first item)))
      (assoc game-state (first item) #{(last item)})
      (update-in game-state [(first item)] conj (last item)))))

(defn parse-board [board-lines]
  (let [lines (str/split-lines board-lines)]
    (reduce to-game-state {} (map-indexed parse-row lines))))

(defn find-path [board start end]
  [])
