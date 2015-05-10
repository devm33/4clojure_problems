(ns problems.117)

; for science!

; maze problem

; simple bfs soln

(defn __ [maze]
  (loop [visited #{}
         queue (list (let [r (some #(when (.contains % "M") %) maze)]
                       (vector (.indexOf maze r) (.indexOf r "M"))))]

    ))


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

