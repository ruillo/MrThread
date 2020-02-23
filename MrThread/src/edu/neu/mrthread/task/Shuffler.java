package edu.neu.mrthread.task;

import edu.neu.mrthread.core.KeyDistribution;
import edu.neu.mrthread.core.Server;
import edu.neu.mrthread.core.ServerRegistry;
import edu.neu.mrthread.core.Worker;
import edu.neu.mrthread.core.WorkerLocalPool;
import edu.neu.mrthread.store.Shard;
import edu.neu.mrthread.store.opt.Move;

public class Shuffler extends Task {
	private Move move;

	public Shuffler() {
		move = new ShuffleMove(context.getLocalShard(), context.getJobName(), context.getOrder() + "",
				context.getWorkid());
		move.connect();
	}

	public void shuffle() {
		move.perform();
		move.close();
	}

	class ShuffleMove extends Move {
		private Worker worker;
		private Server server;

		public ShuffleMove(Shard shard, String space, String table, int workId) {
			super(shard, space, table);
			this.server = ServerRegistry.local();
			this.worker = WorkerLocalPool.worker(workId);
		}

		@Override
		public boolean check(String key) {
			return KeyDistribution.takeKey(server, worker, key);
		}

	}

}
