(ns problems.124)

(def __
  (let [dirs (for [x (range -1 2) y (range -1 2)
                   :when (not (and (zero? x) (zero? y)))] [x y])]
    (fn [board piece])
    ))

(def tb '[[e e e e]
          [e w b e]
          [e b w e]
          [e e e e]])

(defn opp [piece] (if (= 'w piece) 'b 'w))

(defn move [p d board piece]
  (letfn [(incd [p] (map + p d))]
  (when (= 'e (get-in board p))
    (let [opp-pieces (take-while
                       #(= (opp piece) (get-in board %))
                       (iterate incd (incd p)))]
      (when (= piece (get-in board (incd (last opp-pieces))))
        opp-pieces)))))

(move [1 1] [1 0] tb 'b)

