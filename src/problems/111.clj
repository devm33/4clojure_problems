(ns problems.111)

; crossword puzzle

(defn __ [word board]
  (letfn [(fit? [spot]
            (when (= (count word) (count spot))
              (apply = true (map #(or (= \_ %2) (= %1 %2)) word spot))))
          (map-spots [row] (map #(.replace % " " "") (.split row "#")))]
    (boolean (some true? (map fit? (mapcat map-spots (concat board (apply map str board))))))))


(assert (= true  (__ "the" ["_ # _ _ e"])))
(assert (= false (__ "the" ["c _ _ _"
                    "d _ # e"
                    "r y _ _"])))
(assert (= true  (__ "joy" ["c _ _ _"
                    "d _ # e"
                    "r y _ _"])))
(assert (= false (__ "joy" ["c o n j"
                    "_ _ y _"
                    "r _ _ #"])))
(assert (= true  (__ "clojure" ["_ _ _ # j o y"
                        "_ _ o _ _ _ _"
                        "_ _ f _ # _ _"])))


; split row into word spots
(defn map-spots [row]
  (map #(.replace  % " " "") (.split row "#")))

(def test-row "t e s _ # _ t")
(assert (= ["tes_" "_t"] (map-spots test-row)))

; check if word fits in spot
(defn fits? [word spot]
  (when (= (count word) (count spot))
    (apply = true (map #(or (= \_ %2) (= %1 %2)) word spot))))

(def test-word "clojure")
(def test-spot "__oju__")
(def test-noword "cloj")
(assert (fits? test-word test-spot))
(assert (not (fits? test-noword test-spot)))

; gather rows and cols for board
(defn add-cols [board]
  (concat board (apply map str board)))

(assert (= (add-cols ["test"
                      "_e_t"
                      "t_s_"])
           ["test" "_e_t" "t_s_" "t_t" "ee_" "s_s" "tt_"]))


; convert rows into spots
(assert (= (mapcat map-spots ["a # t e _ t" "t _ s t # t w _"])
   ["a" "te_t" "t_st" "tw_"]))
