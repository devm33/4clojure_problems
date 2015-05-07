(ns problems.94)


; game of life

; rules:
; 1. each cell has <= 8 neighbors (horizontal, vertical, diagonal)
; 2. cells with > 3 or < 2 living neighbors dies
; 3. dead cells with exactly 3 living neighbors comes to life

(defn __ [board]
  (letfn [(alive [[r c]]
            (when (< -1 r (count board))
              (let [row (nth board r)]
                (when (< -1 c (count row))
                  (= \# (nth row c))))))
          (living-neighbors [r c]
            (- (count
                 (filter alive (for [dr (range -1 2) dc (range -1 2)]
                                 [(+ r dr) (+ c dc)])))
               (if (alive [r c]) 1 0)))
          (step [r c]
            (let [n (living-neighbors r c)]
              (if (alive [r c])
                (if (<= 2 n 3) \# " ")
                (if (= 3 n) \# " "))))]
    (map-indexed
      (fn [r row]
        (apply str (map-indexed
                     (fn [c cell]
                       (step r c)) row))) board)))

(assert (= (__ ["      "
                " ##   "
                " ##   "
                "   ## "
                "   ## "
                "      "])
           ["      "
            " ##   "
            " #    "
            "    # "
            "   ## "
            "      "]))


; partials

(def test-board  ["   "
                  " # "
                  "# #"])

(assert (= \2 (nth "01234" 2)))

(defn alive? [board [r c]]
  (when (< -1 r (count board))
    (let [row (nth board r)]
      (when (< -1 c (count row))
        (= \# (nth row c))))))

(assert (alive? test-board [1 1]))
(assert (not (alive? test-board [0 2])))
(assert (not (alive? test-board [3 2])))

(defn living-neighbors [board [r c]]
  (- (count (filter (partial alive? board)
                    (for [dr (range -1 2) dc (range -1 2)]
                      [(+ r dr) (+ c dc)])))
     (if (alive? board [r c]) 1 0)))

(assert (= 1 (living-neighbors test-board [0 0])))
(assert (= 2 (living-neighbors test-board [1 1])))
(assert (= 3 (living-neighbors test-board [2 1])))

(defn step [board [r c]]
  (let [n (living-neighbors board [r c])]
    (if (alive? board [r c])
      (if (<= 2 n 3) \# " ")
      (if (= 3 n) \# " "))))

(assert (= \# (step test-board [1 1])))
(assert (= \# (step test-board [2 1])))
(assert (= " " (step test-board [0 0])))
(assert (= " " (step test-board [0 2])))

(assert
  (=
   (map-indexed
     (fn [r row]
       (apply str (map-indexed
                    (fn [c cell] (step test-board [r c])) row)))
     test-board)
   ["   "
    " # "
    " # "]))
