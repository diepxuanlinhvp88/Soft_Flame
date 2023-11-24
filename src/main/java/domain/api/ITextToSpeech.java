package domain.api;

import javax.speech.AudioException;
import javax.speech.EngineException;
import javax.speech.EngineStateError;
import java.beans.PropertyVetoException;

public interface ITextToSpeech {
    public void init(String voiceName) throws EngineException, AudioException, PropertyVetoException;

    public void terminate() throws EngineException, EngineStateError;

    public void doSpeak(String speakText) throws InterruptedException, EngineException, AudioException;
}
