import com.program.board.demo.map.Mapping;
import com.program.board.demo.model.Epic;
import com.program.board.demo.model.Feature;
import com.program.board.demo.model.dtos.FeatureDto;
import com.program.board.demo.repository.EpicRepository;
import com.program.board.demo.repository.FeatureRepository;
import com.program.board.demo.service.ApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ApiServiceTest {

    @Mock
    private FeatureRepository featureRepository;

    @Mock
    private EpicRepository epicRepository;

    @Mock
    private Mapping mapping;

    @InjectMocks
    private ApiService apiService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetEpics() {
        List<Epic> expectedEpics = new ArrayList<>();
        when(epicRepository.findAll()).thenReturn(expectedEpics);

        List<Epic> actualEpics = apiService.getEpics();

        assertEquals(expectedEpics, actualEpics);
    }

    @Test
    public void testGetEpicById_WhenEpicExists() {
        Epic expectedEpic = new Epic();
        when(epicRepository.findById(anyLong())).thenReturn(Optional.of(expectedEpic));

        Object result = apiService.getEpicById(1L);

        assertEquals(expectedEpic, result);
    }

    @Test
    public void testGetEpicById_WhenEpicDoesNotExist() {
        when(epicRepository.findById(anyLong())).thenReturn(Optional.empty());

        Object result = apiService.getEpicById(1L);

        assertEquals("Epic not found.", result);
    }

    @Test
    public void testSaveFeature() {
        FeatureDto featureDto = new FeatureDto();
        Feature feature = new Feature();
        when(mapping.featureDto(any(Feature.class))).thenReturn(featureDto);

        FeatureDto result = apiService.saveFeature(featureDto);

        assertEquals(featureDto, result);
        verify(featureRepository, times(1)).save(any(Feature.class));
    }

    @Test
    public void testUpdateFeature_WhenFeatureExists() {
        FeatureDto featureDto = new FeatureDto();
        featureDto.setIdFeature(1L);
        Feature existingFeature = new Feature();
        when(featureRepository.findById(anyLong())).thenReturn(Optional.of(existingFeature));

        Object result = apiService.updateFeature(featureDto);

        assertEquals(existingFeature, result);
        verify(featureRepository, times(1)).save(existingFeature);
    }

    @Test
    public void testUpdateFeature_WhenFeatureDoesNotExist() {
        FeatureDto featureDto = new FeatureDto();
        featureDto.setIdFeature(1L);
        when(featureRepository.findById(anyLong())).thenReturn(Optional.empty());

        Object result = apiService.updateFeature(featureDto);

        assertEquals("NOT FOUND", result);
        verify(featureRepository, never()).save(any(Feature.class));
    }

    @Test
    public void testGetAllFeatures() {
        List<Feature> features = new ArrayList<>();
        List<FeatureDto> expectedDtos = new ArrayList<>();
        when(featureRepository.findAll()).thenReturn(features);
        when(mapping.featureDtos(anyList())).thenReturn(expectedDtos);

        List<FeatureDto> actualDtos = apiService.getAllFeatures();

        assertEquals(expectedDtos, actualDtos);
    }

    @Test
    public void testGetFeatureById_WhenFeatureExists() {
        FeatureDto expectedDto = new FeatureDto();
        when(featureRepository.findById(anyLong())).thenReturn(Optional.of(new Feature()));
        when(mapping.featureDto(any(Feature.class))).thenReturn(expectedDto);

        FeatureDto result = apiService.getFeatureById(1L);

        assertEquals(expectedDto, result);
    }

    @Test
    public void testGetFeatureById_WhenFeatureDoesNotExist() {
        when(featureRepository.findById(anyLong())).thenReturn(Optional.empty());

        FeatureDto result = apiService.getFeatureById(1L);

        assertNotNull(result);
        assertNull(result.getIdFeature());
    }

    @Test
    public void testDeleteFeature() {
        Feature feature = new Feature();
        feature.setIdFeature(1L);
        when(featureRepository.findById(anyLong())).thenReturn(Optional.of(feature));

        FeatureDto result = apiService.deleteFeature(1L);

        assertNull(result);
        verify(featureRepository, times(1)).deleteById(1L);
    }
}