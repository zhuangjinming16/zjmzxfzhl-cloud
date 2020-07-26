package com.zjmzxfzhl.gateway.handler;

import com.google.code.kaptcha.Producer;
import com.zjmzxfzhl.common.core.constant.CacheConstants;
import com.zjmzxfzhl.common.core.redis.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DefaultDataBuffer;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author 庄金明
 * @date
 */
@Slf4j
@Component
public class KaptchaHandler implements HandlerFunction<ServerResponse> {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private Producer producer;

    @Override
    public Mono<ServerResponse> handle(ServerRequest serverRequest) {
        final String uuid = serverRequest.queryParam("uuid").get();

        return ServerResponse.status(HttpStatus.OK).contentType(MediaType.IMAGE_JPEG).body(BodyInserters.fromDataBuffers(Mono.create(monoSink -> {
            try {
                byte[] bytes = createCaptcha(uuid);
                DefaultDataBuffer dataBuffer = new DefaultDataBufferFactory().wrap(bytes);
                monoSink.success(dataBuffer);
            } catch (IOException e) {
                log.error("ImageIO write err", e);
                monoSink.error(e);
            }
        })));
    }

    private byte[] createCaptcha(String uuid) throws IOException {
        // 生成验证码
        String text = producer.createText();
        BufferedImage image = producer.createImage(text);

        // 保存到 redis,60秒
        redisUtil.set(CacheConstants.CAPTCHA + uuid, text, 60);

        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        ImageIO.write(image, "jpeg", os);
        return os.toByteArray();
    }

}
