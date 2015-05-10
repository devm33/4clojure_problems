(ns problems.117)

; for science!

; maze problem

; simple bfs soln

(defn __ [maze]
  (letfn [(get_maze_at [[r c]]
            (when (and (< -1 r (count maze)) (< -1 c (count (nth maze r))))
              (nth (nth maze r) c)))]
    (loop [visited #{}
           queue (list (let [r (some #(when (.contains % "M") %) maze)]
                         (vector (.indexOf maze r) (.indexOf r "M"))))]
      (if (empty? queue) false
        (let [cur (last queue)]
          (if (= \C (get_maze_at cur)) true
            (recur
              (conj visited cur)
              (butlast queue)

              ; TODO add neighbors to queue

                   )))))))


(def test-maze ["#######"
                "#     #"
                "#  #  #"
                "#M # C#"
                "#######"])

; find M pos in maze

(defn find_m [maze]
  (let [r (some #(when (.contains % "M") %) maze)]
    (vector (.indexOf maze r) (.indexOf r "M"))))

(find_m test-maze)

