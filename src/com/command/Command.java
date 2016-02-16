package com.command;

import com.exception.InterruptOperationException;

interface Command {
    void execute() throws InterruptOperationException;
}
