package com.losandroides.learn.framework

import io.mockk.MockKVerificationScope
import io.mockk.coVerify
import io.mockk.mockk

inline fun <reified T : Any> relaxedMockk(): T = mockk(relaxed = true)

fun coVerifyOnce(
    verifyBlock: suspend MockKVerificationScope.() -> Unit
) = coVerify(
    exactly = 1,
    verifyBlock = verifyBlock
)

fun coVerifyNever(
    verifyBlock: suspend MockKVerificationScope.() -> Unit
) = coVerify(
    exactly = 0,
    verifyBlock = verifyBlock
)
