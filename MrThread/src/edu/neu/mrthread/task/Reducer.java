package edu.neu.mrthread.task;

/**
 * @author Jie SONG <songjie@mail.neu.edu.cn>
 */
public abstract class Reducer extends Task {

	public abstract void reduce(String key, Iterable<String> lines);

}
