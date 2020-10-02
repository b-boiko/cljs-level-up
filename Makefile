.PHONY: clean run-dev run-fresh

clean:
	rm -rf target

run-dev:
	clj -A:fig

run-fresh: clean run-dev
