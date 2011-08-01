task :play do
  turns = ENV["TURNS"] || "1000"

  Dir.chdir('runner') do
    command = [
      "./playgame.py",
      "--player_seed 42",
      "--end_wait=0.25",
      "--log_dir ../log",
      "--turns #{turns}",
      "--log_stderr",
      "--verbose",
      "--map_file ../maps/symmetric_maps/symmetric_10.map",
      '"clj ../MyBot.clj CLASSPATH=$CLASSPATH:.."',
      '"python ../sample_bots/GreedyBot.py"',
      '"python ../sample_bots/GreedyBot.py"',
      '"python ../sample_bots/LeftyBot.py"'
    ]
    sh command.join(' ')
  end
end

task :zip do
  sh "rm mybot.zip"
  sh "zip mybot.zip *.clj"
end

task :spec do
  classpath = [
   '/usr/local/Library/Formula/clojure-contrib.rb',
   '/usr/local/Cellar/clojure/1.2.1/clojure.jar',
   'lib/speclj-1.4.0-standalone.jar',
   '.'
  ]
  sh "java -cp #{classpath.join(':')} speclj.main"
end
