package by.darchyk.task1.pool;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

import by.darchyk.task1.resource.DataBaseManager;

public class Pool {

	private final static Integer POOL_SIZE = new Integer(5);

	private static Pool instance = null;

	private ArrayBlockingQueue<Connection> resources = new ArrayBlockingQueue<Connection>(POOL_SIZE);

	private static AtomicBoolean create = new AtomicBoolean(false);

	private static ReentrantLock lock = new ReentrantLock();
	
	public final static String LOGIN = "root";
	public final static String PASSWORD = "admin";

	private boolean closePool;

	private Pool() {
		init();
	}

	private void init() {
		DataBaseManager dataBaseManager = new DataBaseManager();
		Properties properties = new Properties();
		properties.setProperty("user", LOGIN);
		properties.setProperty("password",PASSWORD);
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			for (int i = 0; i < POOL_SIZE; i++) {
				Connection cn = null;
				cn = dataBaseManager.getConnection(properties);
				resources.put(cn);
			}
		} catch (SQLException e) {

		} catch (InterruptedException e) {
		}
	}


	public static Pool getInstance() {
		if (!create.get()) {
			try {
				lock.lock();
				if (instance == null) {
					instance = new Pool();
					create.set(true);
				}
			} finally {
				lock.unlock();
			}
		}
		return instance;
	}

	public Connection getResource() {
		if (closePool) {
		}
		Connection res = null;
		try {
			res = resources.take();
		} catch (InterruptedException e) {
			
		}
		return res;

	}

	public void returnResources(Connection res) {
		try {
			resources.put(res);
		} catch (InterruptedException e) {
			
		}
	}

	public void releasePool() {
		final int timeUnit = 5;
		closePool = false;
		Iterator<Connection> iterator = resources.iterator();
		while (iterator.hasNext()) {
			try {
				iterator.next().close();
				TimeUnit.SECONDS.sleep(timeUnit);
			} catch (SQLException | InterruptedException e) {
				
			}
			resources.clear();
		}
	}
}