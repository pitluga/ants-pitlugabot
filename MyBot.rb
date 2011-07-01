require File.expand_path('../ants', __FILE__)

ai=AI.new

MAX_DEPTH = 6

LAND=0
FOOD=1
ENEMY=-1
WATER=0

OPPOSITES = { :E => :W, :W => :E, :N => :S, :S => :N }
DIRECTIONS = [:N, :S, :E, :W]

def log(message)
  $stderr.puts message
end

ai.setup do |ai|
	# your setup code here, if any
end

def evaluate(square)
  if square.land?
    LAND
  elsif square.water?
    WATER
  elsif square.ant?
    ENEMY
  elsif square.neightbor(dir).food?
    FOOD
  else # did I miss something?
    0
  end
end

def search(square, directions, depth=0)
  my_score = evaluate(square)
  return my_score if depth == MAX_DEPTH 

  directions.inject(my_score) do |score, direction|
    score + search(square.neighbor(direction), DIRECTIONS - [OPPOSITES[direction]], depth + 1)
  end
end

ai.run do |ai|

	ai.my_ants.each do |ant|
		scores = [:N, :E, :S, :W].map do |direction|
      [direction, search(ant.square.neighbor(direction), DIRECTIONS - [OPPOSITES[direction]])]
		end

    sorted_scores = scores.sort { |(direction, lscore), (direction, rscore)| lscore <=> rscore }

    log sorted_scores.inspect
    _, high_score = sorted_scores.first
    high_score_directions = sorted_scores.select {|d,s| s == high_score}.map {|d,s| d}
    
    ant.order(high_score_directions[rand(high_score_directions.length)])
	end

end
