(ns problems.119)

(def test-board [[:o :e :e]
                 [:o :x :o]
                 [:x :x :e]])

(defn empty-pos [board]
  (mapcat #(let [r (.indexOf board %)]
             (keep-indexed (fn [i v] (when (= :e v) (vector r i))) %))
          (filter #(.contains % :e) board)))

(defn place-piece [piece pos board]
  (assoc-in board pos piece))

(defn check-win [rows]
  (let
    [checkxo (fn [pred] (if (pred :x) :x (if (pred :o) :o nil)))
     alleq (fn [c coll] (apply = c coll))
     hasgroupeq (fn [c groups] (some (partial alleq c) groups))
     cols (apply map vector rows)
     diag (map-indexed #(nth %2 %1) rows)
     bdiag (map-indexed #(nth %2 (- (dec (count (first rows))) %1)) rows)]
    (checkxo #(hasgroupeq % (concat rows cols [diag bdiag])))))

(set (filter #(check-win (place-piece :x % test-board)) (empty-pos test-board)))

(defn __ [piece board]
  (letfn [(place [pos] (assoc-in board pos piece))
          (check-win [rows]
            (let
              [checkxo (fn [pred] (if (pred :x) :x (if (pred :o) :o nil)))
               alleq (fn [c coll] (apply = c coll))
               hasgroupeq (fn [c groups] (some (partial alleq c) groups))
               cols (apply map vector rows)
               diag (map-indexed #(nth %2 %1) rows)
               bdiag (map-indexed #(nth %2 (- (dec (count (first rows))) %1)) rows)]
              (checkxo #(hasgroupeq % (concat rows cols [diag bdiag])))))]
    (set
      (filter
        #(check-win (place %))
        (mapcat #(let [r (.indexOf board %)]
                   (keep-indexed (fn [i v] (when (= :e v) (vector r i))) %))
                (filter #(.contains % :e) board))))))


(assert (= (__ :x [[:o :e :e]
                   [:o :x :o]
                   [:x :x :e]])
           #{[2 2] [0 1] [0 2]}))

(assert (= (__ :x [[:x :o :o]
                   [:x :x :e]
                   [:e :o :e]])
           #{[2 2] [1 2] [2 0]}))

(assert (= (__ :x [[:x :e :x]
                   [:o :x :o]
                   [:e :o :e]])
           #{[2 2] [0 1] [2 0]}))

(assert (= (__ :x [[:x :x :o]
                   [:e :e :e]
                   [:e :e :e]])
           #{}))

(assert (= (__ :o [[:x :x :o]
                   [:o :e :o]
                   [:x :e :e]])
           #{[2 2] [1 1]}))
