package com.kiparo.domain.executors;

import java.util.concurrent.Executor;

/**
 * Executor implementation can be based on different frameworks or techniques of asynchronous
 * execution, but every implementation will execute the Use Case
 * out of the UI thread.
 */
public interface WorkExecutorThread extends Executor{
}
