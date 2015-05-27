(ns problems.124)

(def __
  (let [dirs (for [x (range -1 2) y (range -1 2)
                   :when (not (and (zero? x) (zero? y)))] [x y])]
    (fn [board piece]
      (let
        [gb (partial get-in board)
         op (if (= 'w piece) 'b 'w)
         +d (fn [d] #(map + % d))
         mv (fn [p]
              (when (= 'e (gb p))
                (set
                  (mapcat
                    (fn [d]
                      (let [p+d (+d d)
                            pcs (take-while
                                  #(= op (gb %))
                                  (iterate p+d (p+d p)))]
                        (when (and (not-empty pcs)
                                   (= piece (gb (p+d (last pcs)))))
                          pcs)))
                    dirs))))]
        (apply merge
          (for [r (range (count board))
                c (range (count (get board r)))]
            (let [ps (mv [r c])]
              (when (not-empty ps)
                {[r c] ps}))))))))

(def tb '[[e e e e]
          [e w b e]
          [e w w e]
          [e b e e]])

(__ tb 'b)
