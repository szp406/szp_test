nohup java -Xms256m -jar -Dloader.path=.,resources,lib armchair-sys.jar >logs/armchair-sys.log &
tail -f logs/armchair-sys.log