/*
 * Copyright © 2020 Apple Inc. and the ServiceTalk project authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.servicetalk.concurrent.api;

import javax.annotation.Nullable;

import static java.util.Objects.requireNonNull;

final class RunnableSingleTerminalSignalConsumer<T> implements SingleTerminalSignalConsumer<T> {
    private final Runnable onFinally;

    RunnableSingleTerminalSignalConsumer(final Runnable onFinally) {
        this.onFinally = requireNonNull(onFinally);
    }

    @Override
    public void onSuccess(@Nullable final T result) {
        onFinally.run();
    }

    @Override
    public void onError(final Throwable throwable) {
        onFinally.run();
    }

    @Override
    public void cancel() {
        onFinally.run();
    }
}
