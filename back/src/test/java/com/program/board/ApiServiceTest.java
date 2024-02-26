import com.program.board.demo.map.Mapping;
import com.program.board.demo.model.Feature;
import com.program.board.demo.model.dtos.FeatureDto;
import com.program.board.demo.repository.FeatureRepository;
import com.program.board.demo.model.dtos.*;
import com.program.board.demo.repository.*;
import com.program.board.demo.model.*;
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
    private Mapping mapping;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskDto taskDto;

    @InjectMocks
    private ApiService apiService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
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

    @Test
    public void testAlterarSprintDeFeature_WhenFeatureDoesNotExist() {
        when(featureRepository.findById(anyLong())).thenReturn(Optional.empty());

        FeatureDto result = apiService.alterarSprintDeFeature(1L, 1L);

        assertNull(result);
        verify(featureRepository, never()).save(any(Feature.class));
    }

    @Test
    public void testAlterarTimeDeFeature_WhenFeatureDoesNotExist() {
        when(featureRepository.findById(anyLong())).thenReturn(Optional.empty());

        FeatureDto result = apiService.alterarTimeDeFeature(1L, 1L);

        assertNull(result);
        verify(featureRepository, never()).save(any(Feature.class));
    }

    @Test
    public void testGetTasksFromFeature_NoTasksFound() {
        when(taskRepository.findTasksFromFeature(anyLong())).thenReturn(new ArrayList<>());

        List<TaskDto> result = apiService.getTasksFromFeature(1L);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testUpdateTask_WhenTaskDoesNotExist() {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(1L);
        taskDto.setFeatureId(1L);
        when(taskRepository.findById(anyLong())).thenReturn(Optional.empty());

        TaskDto result = apiService.updateTask(taskDto);

        assertNull(result);
        verify(taskRepository, never()).save(any(Task.class));
    }
}