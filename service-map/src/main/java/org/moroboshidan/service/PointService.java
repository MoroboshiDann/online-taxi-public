package org.moroboshidan.service;

import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.request.PointRequest;
import org.moroboshidan.remote.TrackClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointService {
    @Autowired
    private TrackClient trackClient;
    public ResponseResult upload(PointRequest pointRequest) {
        return trackClient.uploadTrackPoints(pointRequest);
    }
}
